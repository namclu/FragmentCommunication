package com.example.fragmentcommunicationexample

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class FragmentB : Fragment() {
    private var listener: FragmentB.FragmentBListener? = null
    private var editText: EditText? = null
    private var buttonOK: Button? = null

    interface FragmentBListener {
        fun onInputBSent(input: CharSequence)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_b, container, false)
        editText = view.findViewById(R.id.et_field)
        buttonOK = view.findViewById(R.id.btn_ok)

        buttonOK?.setOnClickListener {
            val input = editText!!.text
            if (input != null) {
                listener?.onInputBSent(input)
            }
        }
        return view
    }

    // The context is the Activity being passed
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FragmentB.FragmentBListener) {
            listener = context
        } else throw RuntimeException(context.toString() + " must implement FragmentBListener")
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