/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * SPDX-FileCopyrightText: Copyright 2024 Fcitx5 for Android Contributors
 */

package org.fcitx.fcitx5.android.data.clipboard.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = AccountMessageEntry.TABLE_NAME)
data class AccountMessageEntry(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val userId:String,
    val userName:String,
    val message:String,
    val time:Long
){
    companion object {
        const val TABLE_NAME = "accountMessage"
    }
}
