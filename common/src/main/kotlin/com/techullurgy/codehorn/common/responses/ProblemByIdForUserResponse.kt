package com.techullurgy.codehorn.common.responses

import com.techullurgy.codehorn.common.dto.ProblemDTO

data class ProblemByIdForUserResponse(
    val problem: ProblemDTO
)