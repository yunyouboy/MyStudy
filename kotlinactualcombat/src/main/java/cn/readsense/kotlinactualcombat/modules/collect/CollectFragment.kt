package cn.readsense.kotlinactualcombat.modules.collect

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import cn.readsense.kotlinactualcombat.R
import cn.readsense.kotlinactualcombat.adapter.CollectAdapter
import cn.readsense.kotlinactualcombat.base.BaseFragment
import cn.readsense.kotlinactualcombat.database.Student
import cn.readsense.kotlinactualcombat.modules.collect.inter.CollectPresenter
import cn.readsense.kotlinactualcombat.modules.collect.inter.CollectView
import kotlinx.android.synthetic.main.fragment_collect.*

class CollectFragment : BaseFragment<CollectPresenter>(), CollectView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: View = inflater.inflate(R.layout.fragment_collect, container, false)
        return root ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    // 类似于  Java的构造代码块
    init {
        setHasOptionsMenu(true);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //　TODO Insert　动作
        addData.setOnClickListener {
            val names = arrayOf<String> (
                    "乔峰",
                    "段誉",
                    "虚竹",
                    "慕容复",
                    "张三",
                    "李四",
                    "王五",
                    "赵六",
                    "初七",
                    "杜子腾",
                    "王小二",
                    "李大奇"
            )
            val ages = arrayOf<Int> (
                    43,
                    24,
                    19,
                    83,
                    64,
                    21,
                    56,
                    32,
                    17,
                    32,
                    45,
                    14
            )
            for (index in names.indices) {
                val stu = Student(names[index], ages[index])
                presenter.requestInsert(stu)
            }
        }

        // TODO 全部删除动作
        clearData.setOnClickListener {
            presenter.requestDeleteAll()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.personal_menu, menu)
    }

    override fun createPresenter(): CollectPresenter = CollectPresenterImpl(this@CollectFragment)

    override fun createOK() {
        presenter.requestDeleteAll()
    }

    override fun recycle() {
        presenter.unAttachView()
    }

    override fun showResult(result: Boolean) {
        if (result) {
            presenter.requestQueryAll()
        }
    }

    override fun showResultSuccess(result: List<Student>?) {
        recyclerView.layoutManager = LinearLayoutManager(myActivity)
        val adapter = CollectAdapter()
        if (null != result) {
            adapter.allStudents = result
        }
        recyclerView.adapter = adapter
    }
}