package com.practice.circleci

import org.scalatest.{Outcome, fixture}


class TestSuite1 extends fixture.FunSuite {
  override protected def withFixture(test: OneArgTest): Outcome = {
    val s1 = "1"
    val s2 = "2"
    val s3 = "3"
    println("Running fixture")
    test(s1,s2,s3)
  }

  override type FixtureParam = (String,String,String)

  test("first test"){ f =>
    println("Running test 1")
    val (string1,string2,string3) = f
    assertResult(f._1)(string1)
    assertResult(f._2)(string2)
    assertResult(f._3)(string3)
  }


  test("Second test"){f =>
    println("Running test 2")
    assert(true)
  }
}
