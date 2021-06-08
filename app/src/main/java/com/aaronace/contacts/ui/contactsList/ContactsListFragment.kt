package com.aaronace.contacts.ui.contactsList

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.aaronace.contacts.MainActivity

import com.aaronace.contacts.R
import com.aaronace.contacts.databinding.FragmentContactsListBinding
import com.aaronace.contacts.di.ViewModelProviderFactory
import com.aaronace.contacts.util.OPTIONS
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ContactsListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: ContactsListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentContactsListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(ContactsListViewModel::class.java)

        val adapter = ContactsAdapter(context!!, ContactsClickListener {
            val id = it.id
            this.findNavController().navigate(ContactsListFragmentDirections
                .actionContactsListFragmentToContactDetailsFragment(id))
        })

        binding.contactList.adapter = adapter

        subscribeUI(adapter)

        binding.fab.setOnClickListener {
            it.findNavController().navigate(R.id.action_contactsListFragment_to_addContactFragment, null, OPTIONS)
        }
        setHasOptionsMenu(true)
        return binding.root

    }

    private lateinit var searchView: SearchView

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)

        // Initialize Search View
        searchView = menu?.findItem(R.id.searchView)?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.findContactByName(query!!)
                //hideKeyboard()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        searchView.setOnCloseListener(object: SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                viewModel.getAllContacts
                searchView.onActionViewCollapsed()
                //hideKeyboard()
                return true
            }
        })


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutMe  -> requireView().findNavController().
            navigate(ContactsListFragmentDirections.actionContactsListFragmentToAboutFragment())
        }
        return super.onOptionsItemSelected(item)
    }


    private fun subscribeUI(adapter: ContactsAdapter) {
        viewModel.getAllContacts.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = "Contacts Book"
    }
}
