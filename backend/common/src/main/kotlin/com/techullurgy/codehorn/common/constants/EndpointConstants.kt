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
        }
    }
}

fun EndpointConstants.Public.Problems.getProblemByIdForUserUri(userId: String, problemId: String): String {
    val uri = "http://${ConsulConstants.ServiceNames.PROBLEMS_SERVICE}/problems/${problemId}?userId=${userId}"
    return uri
}