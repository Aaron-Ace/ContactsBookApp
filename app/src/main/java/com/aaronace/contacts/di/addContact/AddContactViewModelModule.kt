package com.aaronace.contacts.di.addContact

import androidx.lifecycle.ViewModel
import com.aaronace.contacts.di.ViewModelKey
import com.aaronace.contacts.ui.addContact.AddContactViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AddContactViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddContactViewModel::class)
    abstract fun bindAddContactViewModel(addContactViewModel: AddContactViewModel): ViewModel

}