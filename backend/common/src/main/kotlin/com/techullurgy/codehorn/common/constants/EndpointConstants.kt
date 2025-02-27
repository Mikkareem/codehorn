package com.techullurgy.codehorn.common.constants

object EndpointConstants {

    object Internal {
        object Problems {
            const val GET_PROBLEM_BY_ID_FOR_CODE_EXECUTION = "/problems/{id}/execution"
        }
    }

    object Public {
        object Problems {
            const val GET_PROBLEM_BY_ID_FOR_USER = "/problems/{id}"
            const val GET_SNIPPET_FOR_PROBLEM_FOR_LANGUAGE = "/problems/{id}/snippet"
        }
    }
}

fun EndpointConstants.Public.Problems.getProblemByIdForUserUri(userId: String, problemId: String)
    = "http://${ConsulConstants.ServiceNames.PROBLEMS_SERVICE}/problems/${problemId}?userId=$userId"

fun EndpointConstants.Public.Problems.getSnippetForProblemForLanguageUri(userId: String, problemId: String, language: String)
    = "http://${ConsulConstants.ServiceNames.PROBLEMS_SERVICE}/problems/${problemId}/snippet?userId=$userId&language=$language"