/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * SPDX-FileCopyrightText: Copyright 2021-2023 Fcitx5 for Android Contributors
 */
package org.fcitx.fcitx5.android.ui.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import org.fcitx.fcitx5.android.FcitxApplication
import org.fcitx.fcitx5.android.data.clipboard.db.AccountCard
import org.fcitx.fcitx5.android.data.clipboard.db.AccountMessageEntry
import org.fcitx.fcitx5.android.databinding.FragmentSetupBinding
import org.fcitx.fcitx5.android.ui.setup.SetupPage.Companion.isLastPage
import org.fcitx.fcitx5.android.utils.serializable
import java.util.UUID

class SetupFragment : Fragment() {

    private val viewModel: SetupViewModel by activityViewModels()

    private lateinit var binding: FragmentSetupBinding

    private val page: SetupPage by lazy { requireArguments().serializable(PAGE)!! }

    private var isDone: Boolean = false
        set(value) {
            if (value && page.isLastPage()) {
                viewModel.isAllDone.value = true
            }
            with(binding) {
                hintText.text = page.getHintText(requireContext())
                actionButton.visibility = if (value) View.GONE else View.VISIBLE
                actionButton.text = page.getButtonText(requireContext())
                actionButton.setOnClickListener {
                    val userName = binding.etUserName.text.toString()
                    if (userName.isNullOrEmpty()){
                        ToastUtils.showShort("请输入昵称")
                        return@setOnClickListener
                    }

                    val account = AccountCard(
                        SPUtils.getInstance().getString("publicKey"),
                        UUID.randomUUID().toString(),
                        userName
                    )
                    FcitxApplication.getInstance().setAccount(account)

                    page.getButtonAction(requireContext())
                }
                doneText.visibility = if (value) View.VISIBLE else View.GONE
            }
            field = value
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSetupBinding.inflate(inflater)
        sync()
        return binding.root
    }

    // called on window focus changed
    fun sync() {
        isDone = page.isDone()
    }

    override fun onResume() {
        super.onResume()
        sync()
    }

    companion object {
        const val PAGE = "page"
    }

}