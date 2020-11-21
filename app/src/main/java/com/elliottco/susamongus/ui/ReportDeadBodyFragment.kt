package com.elliottco.susamongus.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.elliottco.susamongus.R
import com.elliottco.susamongus.model.DeadBodyReport
import com.elliottco.susamongus.model.PlayerColor
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Fragment for reporting a dead body
 */
@AndroidEntryPoint
class ReportDeadBodyFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var susBehavior: DeadBodyReport
    private lateinit var titleEditText: EditText
    private lateinit var playerColorSpinner: Spinner
    private lateinit var locationSpinner: Spinner
    private lateinit var reportButton: Button
    private lateinit var selectedPlayerImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        susBehavior = DeadBodyReport()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suspicious_behavior, container, false).apply {
            titleEditText = findViewById(R.id.titleEditText)
            playerColorSpinner = findViewById(R.id.playerColorSpinner)
            locationSpinner = findViewById(R.id.locationSpinner)
            reportButton = findViewById(R.id.reportButton)
            selectedPlayerImage = findViewById(R.id.selectedPlayerImage)

            playerColorSpinner.onItemSelectedListener = this@ReportDeadBodyFragment
            locationSpinner.onItemSelectedListener = this@ReportDeadBodyFragment

            ArrayAdapter.createFromResource(
                context,
                R.array.player_colors,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                playerColorSpinner.adapter = adapter
            }

            ArrayAdapter.createFromResource(
                context,
                R.array.room_names,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                locationSpinner.adapter = adapter
            }
        }
    }

    override fun onStart() {
        super.onStart()

        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                susBehavior.location = p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }
        }

        titleEditText.addTextChangedListener(titleWatcher)

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        p0?.id?.let {
            when(it) {

                // PlayerColorSpinner was selected
                R.id.playerColorSpinner -> {
                    // Get the String value at the index (p2) and display image in selectedPlayerImage
                    val colors = resources.getStringArray(R.array.player_colors)

                    selectedPlayerImage.setImageResource(
                        when(colors[p2]) {
                            PlayerColor.RED.value -> R.drawable.red_player
                            PlayerColor.BLUE.value -> R.drawable.blue_player
                            PlayerColor.YELLOW.value -> R.drawable.yellow_player
                            PlayerColor.BLACK.value -> R.drawable.black_player
                            PlayerColor.PURPLE.value -> R.drawable.purple_player
                            PlayerColor.WHITE.value -> R.drawable.white_player
                            PlayerColor.GREEN.value -> R.drawable.green_player
                            else -> R.drawable.orange_player
                        }
                    ).also {
                        selectedPlayerImage.visibility = View.VISIBLE
                    }

                }
                // LocationSpinner was selected
                else -> Toast.makeText(context, "Location set: ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}