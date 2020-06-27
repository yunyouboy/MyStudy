package cn.readsense.kotlinactualcombat.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.readsense.kotlinactualcombat.R
import cn.readsense.kotlinactualcombat.database.Student

class CollectAdapter : RecyclerView.Adapter<CollectAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_id: TextView = itemView.findViewById(R.id.tv_id)
        var tv_name: TextView = itemView.findViewById(R.id.tv_name)
        var tv_age: TextView = itemView.findViewById(R.id.tv_age)
    }

    var allStudents: List<Student> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView: View = layoutInflater.inflate(R.layout.item_collect_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allStudents.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val student: Student = allStudents.get(position)
        holder.tv_id.text = "${position + 1}"
        holder.tv_name.text = student.name.toString()
        holder.tv_age.text = student.age.toString()
        holder.itemView.setOnClickListener {
            val uri: Uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=" + holder.tv_name.text)
            val intent: Intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            holder.itemView.context.startActivity(intent)
        }
    }


}