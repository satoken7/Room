package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = HeroDatabase.getInstance(this)
        val heroDao = database.heroDao()

        val data: MutableList<Map<String,String>> = mutableListOf()
        heroDao.deleteAll()
        val newHeros = listOf(
            mapOf(
                "image" to "aka",
                "name"  to "赤レンジャー",
                "detail" to "熱血で頼れるリーダー"),
            mapOf(
                "image" to "ao",
                "name"  to "青レンジャー",
                "detail" to "熱血で頼れるリーダー"),
            mapOf(
                "image" to "ki",
                "name"  to "黄レンジャー",
                "detail" to "熱血で頼れるリーダー"),
            mapOf(
                "image" to "midori",
                "name"  to "緑レンジャー",
                "detail" to "熱血で頼れるリーダー"),
            mapOf(
                "image" to "pink",
                "name"  to "ピンクレンジャー",
                "detail" to "熱血で頼れるリーダー"))

        var heroList:List<Hero> = mutableListOf()


        if (heroList.size == 0) {
            for (newHero in newHeros) {
                heroDao.insert(
                    Hero(
                        0, resources.getIdentifier(
                            newHero["image"],
                            "drawable", packageName
                        ).toString(),
                        newHero["name"].toString(),
                        newHero["detail"].toString()
                    )
                )
            }
            heroList = heroDao.getAll()
        }


        for (i in 1..20) {
            for (d in heroList) {
                data.add(mutableMapOf("image" to d.image, "name" to d.name, "detail" to d.detail))
            }
        }

        val list = findViewById<ListView>(R.id.list)
        list.adapter = SimpleAdapter(
            this,
            data,
            R.layout.list_item,
            arrayOf("image","name","detail"),
            intArrayOf(R.id.image, R.id.name, R.id.detail)
        )


        list.setOnItemClickListener { adapterView, _, i, _ ->
            val item = adapterView.getItemAtPosition(i)as MutableMap<*, *>
            val secondImage = item["image"]
            val secondName = item["name"] as String
            val secondDetail = item["detail"] as String
            Intent(this, SecondActivity::class.java).apply{
                putExtra("NAME", secondName)
                putExtra("DETAIL",secondDetail)
                putExtra("IMAGE", secondImage.toString().toInt())
                startActivity(this)
            }
        }
    }

}