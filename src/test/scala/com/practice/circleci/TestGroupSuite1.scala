package com.practice.circleci

import org.scalatest.{DoNotDiscover}
import org.scalatest.funsuite.AnyFunSuite


@DoNotDiscover
class TestGroupSuite1 extends AnyFunSuite{


  test("Checking something 1"){
    Thread.sleep(10000)
    println("Running Suite 1")
  }

}
