package com.practice.circleci

import java.util.concurrent.TimeUnit

import monix.execution.Cancelable
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers
//import scala.concurrent.ExecutionContext.Implicits.global
import monix.execution.Scheduler.{global => scheduler}
import monix.eval.Task
import scala.util.{Success, Failure}
import monix.execution.CancelableFuture
import monix.execution.Scheduler.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.Future

/*
Suite to practice Cats
 */
class TestSuite9 extends AnyFlatSpecLike with Matchers{


  it should "Future. traverse" in {
    val list = List(1,2,3,4,5,6,7,7)
    val f: Int => Future[Int] = i => Future(i+1)
    Future.traverse(list)(f)
  }

  it should "Scheduler runnable" in {
    //Once run, it can not be stoped it
    scheduler.execute(() => {
      val thread = Thread.currentThread().getName
      println(s"Hello, world! $thread")
    })
  }


  it should "cancelable - schedule Once- future" in {
    val cancelable: Cancelable = scheduler.scheduleOnce(5, TimeUnit.SECONDS,
       () => {
         println("Thread into schedule "+Thread.currentThread().getName)
       }
    )

    //Another friendly way to do the same than above.
    val cancellableV2 = scheduler.scheduleOnce(5.seconds) {
      println("Hello, world!")
    }

    println(s"Main thread ${Thread.currentThread().getName}")
    //cancelable.cancel()
    Thread.sleep(6000)
  }

  // The value cancelable always will live as long as the we do not run cancelable.cancel
  it should "cancelable - schedule with fix delay- future" in {
    val cancelable = scheduler.scheduleWithFixedDelay(
      3, 5, TimeUnit.SECONDS, () => {
        println(s"Thread into schedule ${Thread.currentThread().getName}")
        //throw new Exception("Fail")
        }
      )
    println(s"Main thread ${Thread.currentThread().getName}")
    //cancelable.cancel()
    Thread.sleep(20000)

    //Another friendly way to do the same than above.
    scheduler.scheduleWithFixedDelay(3.seconds, 5.seconds) {
      println("Fixed delay task")
    }
  }

  it should "task runAsync approach" in {

    println(s"Thread before task instance ${Thread.currentThread().getName}")
    val task: Task[Int] = Task {
      println(s"Thread into task ${Thread.currentThread().getName}")
      1 + 1
    }
    println(s"Thread after task instance ${Thread.currentThread().getName}")

    // Tasks get evaluated only on runToFuture!
    // Callback style:
    val cancelable = task.runAsync{ result =>
      Thread.sleep(5000)
      println(s"Thread into callback ${Thread.currentThread().getName}")
      result match {
        case Right(value) =>
          println(value)
        case Left(ex) =>
          System.out.println(s"ERROR: ${ex.getMessage}")
      }
    }
    //Thread.sleep(5000)
  }

  //Just the call back is run in a different thread.
  it should "task runToFuture approach" in {

    println(s"Thread before task instance ${Thread.currentThread().getName}")
    val task: Task[Int] = Task {
      println(s"Thread into task ${Thread.currentThread().getName}")
      1 + 1
    }
    println(s"Thread after task instance ${Thread.currentThread().getName}")

    // Tasks get evaluated only on runToFuture!
    // Callback style:
    val cancelable = task.runToFuture
    cancelable.onComplete{ result =>
      Thread.sleep(5000)
      println(s"Thread into callback ${Thread.currentThread().getName}")
      result match {
        case Success(value) =>
          println(value)
        case Failure(ex) =>
          System.out.println(s"ERROR: ${ex.getMessage}")
      }
    }
    //cancelable.cancel()
    Thread.sleep(7000)
  }

//Task execution and call back are run in a different thread.
  it should "task with delay approach" in {
    val task = Task{
      println(s"Thread into task ${Thread.currentThread().getName}" )
      1 + 1
    }.delayExecution(5.second)

    println(s"Main thread ${Thread.currentThread().getName}")

    val result: CancelableFuture[Int] = task.runToFuture
      result.onComplete{
        case Success(value) => println(s"Thread success value ${Thread.currentThread().getName}")
        case Failure(ex) => println(ex.getMessage)
      }

    Thread.sleep(5000)
    // If we change our mind
    //result.cancel()
  }

  //Same thread for all
  it should "Task with inmediatly execution" in {
    println(s"Thread before task instance ${Thread.currentThread().getName}")
    val task = Task.eval{
      println(s"Thread into task instance ${Thread.currentThread().getName}")
      "Hello!"
    }
    println(s"Thread after task instance ${Thread.currentThread().getName}")
    task.runSyncStep match {
      case Left(task) =>
        // No luck, this Task really wants async execution
        task.runToFuture.foreach(r => println(s"Async: $r"))
      case Right(result) =>
        println(s"Thread with task finished ${Thread.currentThread().getName}")
        println(s"Got lucky: $result")
    }
  }


  it should "Task memoized" in{
    val task = Task.evalOnce { println("Effect1"); "Hello!" }
    val taskv2 = Task.eval{println("Effect2"); "Hello"}.memoize

    task.runAsync{case _ => println("HII")}
    task.runAsync{case _ => println("HII")}
    taskv2.runAsync{case _ => println("Hii2")}
    taskv2.runAsync{case _ => println("Hii2")}
  }

  it should "Task memoized onlySuccess" in{
    val task = Task.eval {
      println("Effect1")
      throw new Exception("Effect incompleted")
    }.memoizeOnSuccess

    task.runAsync{case _ => println("HII")}
    task.runAsync{case _ => println("HII")}
  }

  it should "Task with flatmap" in{

    val task = Task.eval(1+1).flatMap(x=> if(x==2) Task.eval(true) else Task.eval(false))

    task.runAsync {
      case Right(value) => assert(value,"The value is not true")
      case Left(ex) => fail(s"Test failed with exception ${ex.getMessage}")
    }

  }





}
