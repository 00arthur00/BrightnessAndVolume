package com.yechaoa.brightnessandvolume

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_volume.*

class VolumeActivityAll : AppCompatActivity() {

    //音频管理器
    private lateinit var mAudioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setListener()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        mAudioManager = this.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }

    private fun setListener() {

        btn_reset_all.setOnClickListener {
            mAudioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
            tv_mode.text = "当前音量模式：正常"
            this.setStreamVolume()
        }
    }

    /**
     * 设置系统媒体音量
     * setStreamVolume 直接设置音量
     * adjustStreamVolume 步长式设置音量，即10,20,30这样阶梯式
     *
     * 参数1：音量类型
     * 参数2：音量数值
     * 参数3：
     *      AudioManager.FLAG_SHOW_UI 调整音量时显示系统音量进度条 , 0 则不显示
     *      AudioManager.FLAG_ALLOW_RINGER_MODES 是否铃声模式
     *      AudioManager.FLAG_VIBRATE 是否震动模式
     *      AudioManager.FLAG_SHOW_VIBRATE_HINT 震动提示
     *      AudioManager.FLAG_SHOW_SILENT_HINT 静音提示
     *      AudioManager.FLAG_PLAY_SOUND 调整音量时播放声音
     */
    private fun setStreamVolume() {
        val streamTypeList = ArrayList<Int>()
        streamTypeList.add(AudioManager.STREAM_MUSIC)
        streamTypeList.add(AudioManager.STREAM_VOICE_CALL)
        streamTypeList.add(AudioManager.STREAM_SYSTEM)
        streamTypeList.add(AudioManager.STREAM_RING)
        streamTypeList.add(AudioManager.STREAM_MUSIC)
        streamTypeList.add(AudioManager.STREAM_ALARM)
        streamTypeList.add(AudioManager.STREAM_NOTIFICATION)
        for (s in streamTypeList) {
            mAudioManager.setStreamVolume(s, mAudioManager.getStreamMaxVolume(s), AudioManager.FLAG_SHOW_UI)
        }
        mAudioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
    }
}
