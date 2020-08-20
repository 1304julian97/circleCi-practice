package com.practice.circleci

import org.scalatest.Ignore
import org.scalatest.funsuite.AnyFunSuite


class RunCommonSuites extends AnyFunSuite {

  test("Runn all TEst")
  {
    println("Sleeping")
    Thread.sleep(10000)
    println("RUNNING ********************** JULIÁN ********************************* RUNNING")
    new TestMainCommonSuite().execute()
  }

}
