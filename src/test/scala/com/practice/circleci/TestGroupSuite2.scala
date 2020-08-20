package com.practice.circleci

import org.scalatest.{DoNotDiscover, Ignore, Suite}
import org.scalatest.funsuite.AnyFunSuite


@DoNotDiscover
class TestGroupSuite2 extends AnyFunSuite with Suite{

  test("Checking something 2"){
    println("Running Suite 2")
  }

}
