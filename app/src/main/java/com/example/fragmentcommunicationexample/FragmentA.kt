package com.example.fragmentcommunicationexample

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class FragmentA : Fragment() {
    private var listener: FragmentAListener? = null
    private var editText: EditText? = null
    private var buttonOK: Button? = null

    interface FragmentAListener {
        fun onInputASent(input: CharSequence)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_a, container, false)
        editText = view.findViewById(R.id.et_field)
        buttonOK = view.findViewById(R.id.btn_ok)

        buttonOK?.setOnClickListener {
            val input = editText!!.text
            if (input != null) {
                listener?.onInputASent(input)
            }
        }
        return view
    }

    // The context is the Activity being passed
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FragmentAListener) {
            listener = context
        } else throw RuntimeException(context.toString() + " must implement FragmentAListener")
    }

    override fun onDetach() {
        super.onDetach()

        // Release the reference to Activity when the Fragment is detached
        listener = null
    }

    fun updateEditText(updateText: CharSequence) {
        editText!!.setText(updateText)
    }
}