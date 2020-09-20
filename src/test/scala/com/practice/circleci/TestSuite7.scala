package com.practice.circleci


import org.scalatest.Tag
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers

object TAG1 extends Tag("Example Tag")
object TAG2 extends Tag("Example Tag2")


class TestSuit7 extends AnyFlatSpecLike with Matchers{


  trait FirstPart{
    println("Running fixture 1")
    val s1 = "1"
    val s2 = "2"
    val s3 = "3"
  }

  trait SecondPart{
    val s4 = "4"
    val s5 = "5"
    println("Running fixture 2")
  }

  it should "Test one thing without feature" in{
    println("Hello!!")
  }

  it should "Test one with fixture" taggedAs(TAG1,TAG2) in new FirstPart {
    println("Running test1")
    assertResult(s1)("1")
    assertResult(s2)("2")
    assertResult(s3)("3")
  }

  it should "Test 2 with fixture" taggedAs TAG2 in new FirstPart {
    println("Running test2")
    assertResult(s1)("1")
    assertResult(s2)("2")
    assertResult(s3)("3")
  }

  it should "Test 3 with fixture" in new SecondPart {
    println("Running test3")
    assertResult(s4)("4")
    assertResult(s5)("5")
  }

  it should "Test 4 with 2 fixture" in new FirstPart with SecondPart {
    println("Running test4")
    assertResult(s1)("1")
    assertResult(s2)("2")
    assertResult(s3)("3")
    assertResult(s4)("4")
    assertResult(s5)("5")
  }
}
