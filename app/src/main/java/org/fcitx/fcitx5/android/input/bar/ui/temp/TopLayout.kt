/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * SPDX-FileCopyrightText: Copyright 2024 Fcitx5 for Android Contributors
 */

package org.fcitx.fcitx5.android.input.bar.ui.temp

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import com.blankj.utilcode.util.ClipboardUtils
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.SPUtils
import com.google.android.material.button.MaterialButton
import com.hjq.toast.Toaster
import org.fcitx.fcitx5.android.R

class TopLayout(context: Context) : LinearLayout(context) {

    var btnConfirm:MaterialButton
    var btnCard:MaterialButton
    var btnAccount:MaterialButton
    var tvDecryptContent:TextView
    var llContent:LinearLayout
    var ivDel:ImageView
    var spAccount:Spinner
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_top_input,this,false)
        addView(view)
        btnConfirm = view.findViewById(R.id.btn_confirm)
        btnCard = view.findViewById(R.id.btn_card)
        btnAccount = view.findViewById(R.id.btn_account)
        tvDecryptContent = view.findViewById(R.id.tv_decrypt_content)
        llContent = view.findViewById(R.id.ll_content)
        ivDel = view.findViewById(R.id.iv_del)
        spAccount = view.findViewById(R.id.sp_account)

        view.findViewById<MaterialButton>(R.id.btn_key).setOnClickListener {
            val privateKey = SPUtils.getInstance().getString("privateKey","")
            val publicKey = SPUtils.getInstance().getString("publicKey","")

            val map = mutableMapOf<String,String>().apply {
                put("PrivateKey",privateKey)
                put("PublicKey",publicKey)
            }
            ClipboardUtils.copyText(GsonUtils.toJson(map))
            Toaster.show("复制成功")
        }
    }

}