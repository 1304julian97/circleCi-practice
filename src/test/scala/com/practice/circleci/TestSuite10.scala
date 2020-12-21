package com.practice.circleci

import cats.data.{NonEmptyChain, NonEmptyList, ValidatedNec}
import cats.implicits.{catsSyntaxTuple3Semigroupal, catsSyntaxValidatedIdBinCompat0,_}
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers


class TestSuite10 extends AnyFlatSpecLike with Matchers{


  it should "Either condition false" in {
    val either = Either.cond(
      false,
      "Value if condition is true",
      new Exception("Exception if condition is false")
    )
    assert(either.isLeft,"Either should be Left(Exception)")
  }


  it should "Either condition true" in {
    val either = Either.cond(
      true,
      "Value if condition is true",
      new Exception("Exception if condition is false")
    )
    assert(either.isRight,"Either should be Right(String)")
  }

  case class EntityV(att1:String,att2:String, att3:Int)

  type ValidationResult[A] = ValidatedNec[String, A]

  def validateString(str:String): ValidationResult[String] = {
    if (str.length <= 4) str.validNec
    else "String has a incorrect size".invalidNec
  }

  def validateInt(int: Int): ValidationResult[Int] = {
    if(int <= 4) int.validNec
    else "The int hes greater than 4".invalidNec
  }


  it should "Validated Data structure (All object is valid)" in {
    val entity1 = EntityV("str1","str2",1)
    val validationResult: ValidationResult[EntityV] = (validateString(entity1.att1),
      validateString(entity1.att2),
      validateInt(entity1.att3)
      ).mapN(EntityV)
    assert(validationResult.isValid,"The entity should be valid")
  }

  it should "Validated Data structure (Invalid the first and last item)" in {
    val entity1 = EntityV("str455451","str2",10)
    val validationResult: ValidationResult[EntityV] = (validateString(entity1.att1),
      validateString(entity1.att2),
      validateInt(entity1.att3)
      ).mapN(EntityV)
    assert(validationResult.isInvalid,"The entity should not be valid")
  }


  it should "Validated Data structure (Invalid the first and last item) to Either" in {
    val entity1 = EntityV("str455451","str2",10)
    val validationResult: ValidationResult[EntityV] = (validateString(entity1.att1),
      validateString(entity1.att2),
      validateInt(entity1.att3)
      ).mapN(EntityV)
    val eitherValue: Either[NonEmptyChain[String], EntityV] = validationResult.toEither
    eitherValue match {
      case Right(value) => fail(s"The object $value should not exist")
      case Left(value) => println(value.toNonEmptyList.toList.mkString(","))
        assertResult(List("String has a incorrect size","The int hes greater than 4"),"The errors messages returned are not expected")(value.toNonEmptyList.toList)
    }
    assert(validationResult.isInvalid,"The entity should not be valid")
  }

  it should "Create nonEmptyList" in {
    val nonEmptyList = NonEmptyList.one(45)
    assertResult(List(45),"The lists are not the same")(nonEmptyList.toList)
  }

  it should "Create nonEmptyList from List" in {
    val nonEmptyList = NonEmptyList.fromList(List())
    assertResult(None,"The list is empty and for that, the NonEmptyList should be None")(nonEmptyList)
  }

  it should "Create nonEmptyList using of method" in {
    val nonEmptyList = NonEmptyList.of(1,2,3,6)
    assertResult(List(1,2,3,6),"The list is empty and for that, the NonEmptyList should be None")(nonEmptyList.toList)
  }

}
