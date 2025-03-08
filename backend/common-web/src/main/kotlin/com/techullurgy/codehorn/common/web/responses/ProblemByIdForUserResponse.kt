package com.techullurgy.codehorn.common.web.responses

import com.techullurgy.codehorn.common.web.dto.ProblemDTO

data class ProblemByIdForUserResponse(
    val problem: ProblemDTO
)