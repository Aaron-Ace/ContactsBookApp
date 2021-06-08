package com.aaronace.contacts.di.contactDetails

import androidx.lifecycle.ViewModel
import com.aaronace.contacts.di.ViewModelKey
import com.aaronace.contacts.ui.contactDetails.ContactDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContactDetailsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContactDetailsViewModel::class)
    abstract fun bindContactDetailsViewModel(contactDetailsViewModel: ContactDetailsViewModel): ViewModel
}