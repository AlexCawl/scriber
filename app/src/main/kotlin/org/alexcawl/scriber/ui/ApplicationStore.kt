package org.alexcawl.scriber.ui

import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.data.ThemeRepository
import org.alexcawl.scriber.mvi.core.Store

//class ApplicationStore(
//    scope: CoroutineScope,
//    private val themeRepository: ThemeRepository
//) : Store<ApplicationState, ApplicationAction>(scope, ApplicationState.Loading) {
//    override fun createTask(event: ApplicationAction): suspend CoroutineScope.(ApplicationState) -> ApplicationState {
//        return {
//            ApplicationState.Loading
//        }
//    }
//}
