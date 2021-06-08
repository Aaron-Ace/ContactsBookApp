package com.aaronace.contacts.di.contactsList

import androidx.lifecycle.ViewModel
import com.aaronace.contacts.di.ViewModelKey
import com.aaronace.contacts.ui.contactsList.ContactsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContactsListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContactsListViewModel::class)
    abstract fun bindContactsListViewModel(contactsListViewModel: ContactsListViewModel): ViewModel

}