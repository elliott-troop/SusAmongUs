package com.elliottco.susamongus.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elliottco.susamongus.R
import com.elliottco.susamongus.model.DeadBodyReport
import com.elliottco.susamongus.model.PlayerColor
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment displaying list of suspicious behaviors
 */

@AndroidEntryPoint
class ReportListFragment : Fragment() {

    private val susAmongUsViewModel by viewModels<SusAmongUsViewModel>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var adapter: SuspiciousBehaviorAdapter? = null

    companion object {
        fun newInstance() : ReportListFragment {
            return ReportListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sus_report_list, container, false).apply {
            recyclerView = findViewById(R.id.recyclerView)
            fab = findViewById(R.id.fab)

            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayout.HORIZONTAL))

            fab.setOnClickListener {
                Toast.makeText(context, "Creating new report...", Toast.LENGTH_SHORT).show()
            }

            updateUi()
        }
    }

    private fun updateUi() {
        val reports = susAmongUsViewModel.reports
        adapter = SuspiciousBehaviorAdapter(reports)
        recyclerView.adapter = adapter
    }


    private inner class SuspiciousBehaviorHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener {
                showToast()
            }
        }

        private lateinit var deadBodyReport: DeadBodyReport

        val descriptionTextView = itemView.findViewById<TextView>(R.id.titleText)
        val locationTextView = itemView.findViewById<TextView>(R.id.locationText)
        val suspectImageView = itemView.findViewById<ImageView>(R.id.suspectImage)

        fun bind(report: DeadBodyReport) {
            this.deadBodyReport = report
            descriptionTextView.text = report.description
            locationTextView.text = report.location
            suspectImageView.setImageResource(
                when(report.reportedPlayerColor) {
                    PlayerColor.RED -> R.drawable.red_player
                    PlayerColor.BLUE -> R.drawable.blue_player
                    PlayerColor.YELLOW -> R.drawable.yellow_player
                    PlayerColor.BLACK -> R.drawable.black_player
                    PlayerColor.PURPLE -> R.drawable.purple_player
                    PlayerColor.WHITE -> R.drawable.white_player
                    PlayerColor.GREEN -> R.drawable.green_player
                    else -> R.drawable.orange_player
                }
            )
        }

        private fun showToast() {
            Toast.makeText(context, "Opening report: ${deadBodyReport.reportedPlayerColor?.value} in ${deadBodyReport.location}", Toast.LENGTH_SHORT).show()
        }
    }

    private inner class SuspiciousBehaviorAdapter(var susBehaviors: List<DeadBodyReport>)
        : RecyclerView.Adapter<SuspiciousBehaviorHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): SuspiciousBehaviorHolder {
            val view = layoutInflater.inflate(R.layout.item_suspicious_behavior, parent, false)
            return SuspiciousBehaviorHolder(view)
        }

        override fun onBindViewHolder(holder: SuspiciousBehaviorHolder, position: Int) {
            val susBehavior = susBehaviors[position]
            holder.bind(susBehavior)
        }

        override fun getItemCount() = susBehaviors.size

    }

}