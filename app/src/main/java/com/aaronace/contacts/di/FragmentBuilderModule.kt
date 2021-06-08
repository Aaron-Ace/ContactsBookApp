package com.aaronace.contacts.di

import com.aaronace.contacts.di.addContact.AddContactViewModelModule
import com.aaronace.contacts.di.contactDetails.ContactDetailsViewModelModule
import com.aaronace.contacts.di.contactsList.ContactsListViewModelModule
import com.aaronace.contacts.ui.addContact.AddContactFragment
import com.aaronace.contacts.ui.contactDetails.ContactDetailsFragment
import com.aaronace.contacts.ui.contactsList.ContactsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [ContactsListViewModelModule::class])
    abstract fun contributeContactsFragment() : ContactsListFragment

    @ContributesAndroidInjector(modules = [AddContactViewModelModule::class])
    abstract fun contributeAddContactFragment() : AddContactFragment

    @ContributesAndroidInjector(modules = [ContactDetailsViewModelModule::class])
    abstract fun contributeContactDetailsFragment() : ContactDetailsFragment
}