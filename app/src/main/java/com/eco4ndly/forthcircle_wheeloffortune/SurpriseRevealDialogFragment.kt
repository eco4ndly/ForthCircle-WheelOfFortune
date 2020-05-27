package com.eco4ndly.forthcircle_wheeloffortune

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.layout_surprise_reveal.*

/**
 * A Sayan Porya code on 16/05/20
 */
class SurpriseRevealDialogFragment: DialogFragment() {
    private var closeApp = false
    private var dialogCloseListener: DialogFragmentDismissListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_surprise_reveal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closeApp = false
        val textToSHow = arguments?.getString(SURPRISE)
        textToSHow?.let {
            if (it.toLowerCase().contains("death")) {
                tv_bottom.text = getString(R.string.you_dead_message)
                closeApp = true
            }
        }
        tv_you_won.text = textToSHow
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dialogCloseListener?.onClose(closeApp)
    }

    fun setOnDialogCloseListener(dialogCloseListener: DialogFragmentDismissListener) {
        this.dialogCloseListener = dialogCloseListener
    }

    companion object {
        const val SURPRISE = "Surp"
    }
}