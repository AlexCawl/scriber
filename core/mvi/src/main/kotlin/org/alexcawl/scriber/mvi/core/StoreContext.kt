package org.alexcawl.scriber.mvi.core

class StoreContext<S>(val reduce: suspend ((S) -> S) -> Unit)