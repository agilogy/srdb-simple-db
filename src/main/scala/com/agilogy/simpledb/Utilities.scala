package com.agilogy.simpledb

private[simpledb] object Utilities {

  //TODO: This is dangerous. Keep an eye on better ways to detect parameters
  //TODO: We try to avoid detecting '2014-09-23 10:14:23' as parameters. Any other way?
  private val paramsMatcher = """:([0-9a-zA-Z_]+)([^0-9a-zA-Z_.\:']|$)""".r //parameterTypes.keys.map(k => ":" + k).mkString("|").r

  private def extractParameterNames(query: String): Seq[String] = paramsMatcher.findAllMatchIn(query).map(m => m.group(1)).toSeq

  def checkNamedParameters[DT](query: String, parameterNames: Set[String]): Unit = {
    val parameterNamesInQuery: Set[String] = extractParameterNames(query).toSet
    val parametersNotFoundInQuery = parameterNames.diff(parameterNamesInQuery).map(""""%s"""".format(_))
    if (parametersNotFoundInQuery.nonEmpty) {
      throw new IllegalArgumentException("""Some parameters where not found in the query "%s". Provided parameters: %s, parameters in query: %s, missing: %s""".
        format(query, parameterNames, parameterNamesInQuery, parametersNotFoundInQuery.mkString(",")))
    }
    val undefinedParameters = parameterNamesInQuery.diff(parameterNames)
    if (undefinedParameters.nonEmpty) {
      throw new IllegalArgumentException("""The query "%s" contains some parameters with unknown types: %s""".format(query, undefinedParameters))
    }

  }

  def translateNamedParameters(query: String, parameters: Seq[Parameter[_]], arguments: Seq[ParameterValue[_]]): (String, Seq[ParameterValue[_]]) = {
    val names = extractParameterNames(query)
    val orderedArguments = names.map(n => arguments.find(_.parameter.name == n).getOrElse(throw new IllegalArgumentException(s"Parameter $n not found in $arguments")))
    val jdbcQuery = paramsMatcher.replaceSomeIn(query, reMatch => {
      val parameterName = reMatch.group(1)
      val argument = arguments.find(_.parameter.name == parameterName).getOrElse(throw new IllegalArgumentException(s"Parameter $parameterName not found in arguments $arguments"))
      Some(argument.getJdbcPlaceHolder + reMatch.group(2))
    })
    (jdbcQuery, orderedArguments)
  }
}
