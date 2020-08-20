package com.practice.circleci

import org.scalatest.{DoNotDiscover, Ignore, Suite}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers


@DoNotDiscover
class TestGroupSuite1 extends AnyFunSuite with Suite {


  test("Checking something 1"){
    Thread.sleep(10000)
    println("Running Suite 1")
  }

}
