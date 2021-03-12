package com.micheladrien.tt1203.exerciseList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.micheladrien.tt1203.R
import com.micheladrien.tt1203.databinding.FragmentListExerciseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseListFragment : Fragment() {

    private val listViewModel : ExerciseListViewModel by viewModels()

    private var _binding: FragmentListExerciseBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ExerciseListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Vue que ce ViewModel recevra des infos du fragments pop up
        //je suis obligé de mettre l'activité main comme propriétaire
        //relationViewModel = ViewModelProvider(requireActivity()).get(RelationViewModel::class.java)

        Log.d("testAmi", "nous sommes dans le onCreateView")
        _binding = FragmentListExerciseBinding.inflate(inflater, container, false)
        val root = binding.root

        recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_exercise).apply {this.setHasFixedSize(true)}

        listViewModel.exercisesLiveData.observe(viewLifecycleOwner, {

            //Dans l'observe : ecrase et refait le RecyclerViewAdapter
            viewAdapter = ExerciseListAdapter(it)
            // use a linear layout manager
            recyclerView.layoutManager = LinearLayoutManager(context)
            // specify an viewAdapter (see also next example)
            recyclerView.adapter = viewAdapter

        })

        return root

    }
}