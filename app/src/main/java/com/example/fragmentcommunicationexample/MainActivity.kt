package com.example.fragmentcommunicationexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils.replace

class MainActivity : AppCompatActivity(), FragmentA.FragmentAListener, FragmentB.FragmentBListener {

    private var fragmentA: FragmentA? = null
    private var fragmentB: FragmentB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentA = FragmentA()
        fragmentB = FragmentB()

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment_a, fragmentA!!)
            .replace(R.id.container_fragment_b, fragmentB!!)
            .commit()
    }

    override fun onInputASent(input: CharSequence) {
        fragmentB?.updateEditText(input)
    }

    override fun onInputBSent(input: CharSequence) {
        fragmentA?.updateEditText(input)
    }
}
