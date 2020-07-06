package com.practice.circleci

import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers

class TestSuite4 extends AnyFlatSpecLike with Matchers{

  type Fixture = (String, String)

  def testApp(slogan: String = "1")(test: Fixture => Unit)={
    println("Running fixture")
    test((slogan, "2"))
  }

  def testApp2(slogan: String = "1")(test: Fixture => Unit)={
    println("Running fixture2")
    test((slogan, "2"))
  }

  it should "Test1" in testApp(){  f=>
    println("Running test1")
    assertResult("1")(f._1)
    assertResult("2")(f._2)

  }

  it should "Test2" in testApp("3"){ f=>
    println("Running test2")
    assertResult("3")(f._1)
    assertResult("2")(f._2)
  }

  it should "test3" in testApp2("4"){ f=>
    println("Running test3*******************************************")
    assertResult("4")(f._1)
    assertResult("2")(f._2)

  }

  it should "test4" in {
    println("Running without fixture")
    assert(true)
  }


}
