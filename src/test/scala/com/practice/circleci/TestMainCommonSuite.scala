package com.practice.circleci


import org.scalatest.{BeforeAndAfterAll, Outcome, Suites, fixture}

class TestMainCommonSuite extends Suites(new TestGroupSuite1,new TestGroupSuite2,new TestGroupSuite3,new TestGroupSuite4)
  with BeforeAndAfterAll {

  var after = 0
  var before = 0


  override def afterAll(){
    after = after+1
    println(s"TestMainCommonSuite - Running After all ***************** $after")
  }

  override def beforeAll(){
    before = before+1
    println(s"TestMainCommonSuite - Running before all ************* $before")
  }


}

