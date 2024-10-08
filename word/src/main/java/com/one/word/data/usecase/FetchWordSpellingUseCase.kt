package com.one.word.data.usecase

import com.one.coreapp.data.usecase.BaseUseCase
import com.one.state.ResultState
import com.one.task.executeAsyncByFast
import com.one.analytics.logAnalytics
import com.one.word.data.task.spelling.SpellingTask
import com.one.word.entities.Spelling

class FetchWordSpellingUseCase(
    private val list: List<SpellingTask>
) : BaseUseCase<FetchWordSpellingUseCase.Param, ResultState<List<Spelling>>> {

    override suspend fun execute(param: Param?): ResultState<List<Spelling>> {
        checkNotNull(param)

        logAnalytics("fetch word spelling use case ${param.inputCode}")

        return list.executeAsyncByFast(SpellingTask.Param(param.text, param.inputCode))
    }

    data class Param(val text: String, val inputCode: String) : BaseUseCase.Param()
}