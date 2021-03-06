package com.practice.circleci

import org.scalatest.{Outcome, flatspec}

class TestSuite6 extends flatspec.FixtureAnyFlatSpec{
  override protected def withFixture(test: OneArgTest): Outcome = {
    println("Running fixture Suit 6")
    val s1 = "1"
    val s2 = "2"
    test(s1,s2)
  }

  override type FixtureParam = (String,String)


  it should "Test something" in { f =>
    println("Running test1")
    val (string1,string2) = f
    assertResult(f._1)(string1)
    assertResult(f._2)(string2)
  }

  it should "Testing something" in { f=>
    println("Running test2")
    assert(true)
  }


  it should "Testing something 1" in { f=>
    println("Running test3")
    assert(true)
  }


  it should "Testing something 2" in { f=>
    println("Running test4")
    assert(true)
  }

}
