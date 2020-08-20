package com.practice.circleci

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funsuite.FixtureAnyFunSuiteLike
import org.scalatest.{BeforeAndAfterAll, Outcome, Suites, fixture}

class TestMainCommonSuite extends Suites(new TestGroupSuite1,new TestGroupSuite2,new TestGroupSuite3,new TestGroupSuite4)
  with BeforeAndAfterAll {


  override def afterAll(){
    println("Running After all *****************")
  }

  override def beforeAll(){
    println("Running before all *************")
  }


}

