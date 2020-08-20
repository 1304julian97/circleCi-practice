package com.practice.circleci

import org.scalatest.{DoNotDiscover, Ignore, Suite}
import org.scalatest.funsuite.AnyFunSuite


@DoNotDiscover
class TestGroupSuite3 extends AnyFunSuite with Suite {

  test("Checking something 3"){
    println("Running Suite 3")
  }



}
