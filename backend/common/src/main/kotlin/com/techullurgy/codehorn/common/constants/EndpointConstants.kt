package com.techullurgy.codehorn.common.constants

object EndpointConstants {

    object Internal {
        const val HEALTH_CHECK_FOR_CONSUL = "/health-check"

        object Problems {
            const val GET_PROBLEM_BY_ID_FOR_CODE_EXECUTION = "/problems/{id}/execution"
        }
    }

    object Public {
        object Problems {
            const val GET_PROBLEM_BY_ID_FOR_USER = "/problems/{id}"
            const val GET_SNIPPET_FOR_PROBLEM_FOR_LANGUAGE = "/problems/{id}/snippet"
        }

        object CodeExecution {
            const val POST_RUN_CODE_BY_USER = "/problems/{id}/run"
            const val POST_SUBMIT_CODE_BY_USER = "/problems/{id}/submit"
        }

        object LanguageExecution {
            const val POST_CODE_EXECUTION_C = "/code/execution/c"
            const val POST_CODE_EXECUTION_CPP = "/code/execution/cpp"
            const val POST_CODE_EXECUTION_JAVA = "/code/execution/java"
            const val POST_CODE_EXECUTION_PYTHON = "/code/execution/python"
            const val POST_CODE_EXECUTION_JAVASCRIPT = "/code/execution/javascript"
        }
    }
}

fun EndpointConstants.Public.Problems.getProblemByIdForUserUri(userId: String, problemId: String)
    = "http://${ConsulConstants.ServiceNames.PROBLEMS_SERVICE}/problems/${problemId}?userId=$userId"

fun EndpointConstants.Internal.Problems.getProblemByIdForCodeExecution(userId: String, problemId: String)
    = "http://${ConsulConstants.ServiceNames.PROBLEMS_SERVICE}/problems/${problemId}/execution?userId=$userId"

fun EndpointConstants.Public.Problems.getSnippetForProblemForLanguageUri(userId: String, problemId: String, language: String)
    = "http://${ConsulConstants.ServiceNames.PROBLEMS_SERVICE}/problems/${problemId}/snippet?userId=$userId&language=$language"

fun EndpointConstants.Public.CodeExecution.postRunCodeByUser(userId: String, problemId: String)
    = "http://${ConsulConstants.ServiceNames.CODE_EXECUTION_SERVICE}/problems/${problemId}/run?userId=$userId"

fun EndpointConstants.Public.CodeExecution.postSubmitCodeByUser(userId: String, problemId: String)
    = "http://${ConsulConstants.ServiceNames.CODE_EXECUTION_SERVICE}/problems/${problemId}/submit?userId=$userId"

fun EndpointConstants.Public.LanguageExecution.postCodeExecutionC()
    = "http://${ConsulConstants.ServiceNames.C_EXECUTION_SERVICE}${POST_CODE_EXECUTION_C}"

fun EndpointConstants.Public.LanguageExecution.postCodeExecutionCpp()
    = "http://${ConsulConstants.ServiceNames.CPP_EXECUTION_SERVICE}${POST_CODE_EXECUTION_CPP}"

fun EndpointConstants.Public.LanguageExecution.postCodeExecutionJava()
    = "http://${ConsulConstants.ServiceNames.JAVA_EXECUTION_SERVICE}${POST_CODE_EXECUTION_JAVA}"

fun EndpointConstants.Public.LanguageExecution.postCodeExecutionPython()
    = "http://${ConsulConstants.ServiceNames.PYTHON_EXECUTION_SERVICE}${POST_CODE_EXECUTION_PYTHON}"

fun EndpointConstants.Public.LanguageExecution.postCodeExecutionJavascript()
    = "http://${ConsulConstants.ServiceNames.JAVASCRIPT_EXECUTION_SERVICE}${POST_CODE_EXECUTION_JAVASCRIPT}"
