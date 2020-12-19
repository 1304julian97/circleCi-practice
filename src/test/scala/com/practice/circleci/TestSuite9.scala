package com.practice.circleci

import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}
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
}
