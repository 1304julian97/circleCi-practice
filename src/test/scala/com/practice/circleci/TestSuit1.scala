package com.practice.circleci

import org.scalatest.{Outcome, fixture}


class TestSuit1 extends fixture.FunSuite {
  override protected def withFixture(test: OneArgTest): Outcome = {
    test("1","2","3")
  }

  override type FixtureParam = (String,String,String)

  test("first test"){ f =>
    assert(true)
  }
}
