package com.practice.circleci

import org.scalatest.flatspec.AsyncFlatSpecLike
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Future

class TestSuite11 extends Matchers with AsyncFlatSpecLike{


  it should "Future. traverse" in {
    val list = List(1,2,3,4,5,6,7,7)
    val f: Int => Future[Int] = i => {
      Future(i+1)
    }
    val finalFuture: Future[List[Int]] = Future.traverse(list)(f)
    finalFuture.map(x=> assertResult(x,"Bad List")(List(2,3,4,5,6,7,8,8)))
  }
}
