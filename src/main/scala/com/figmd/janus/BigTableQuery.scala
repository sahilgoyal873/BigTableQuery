package com.figmd.janus

import org.apache.hadoop.conf.Configuration
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog

object BigTableQuery {

  def query(spark:SparkSession, catalogPath: String ):DataFrame={

   val catalog = GCSFileUtility.readFile(new Configuration(), catalogPath)
            .mkString("\n")

    val df = spark.sqlContext
      .read
      .options(Map(HBaseTableCatalog.tableCatalog->catalog))
      .format("org.apache.spark.sql.execution.datasources.hbase")
      .load()
    df

  }

}
