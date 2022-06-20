private fun updatetoken(token: String?) {
        val uid = FirebaseAuth.getInstance().uid

        val NODE_SERVER = "http://ec2-13-233-111-116.ap-south-1.compute.amazonaws.com:3001/savetoken"
        val jsonObject = JSONObject()
        try {
            jsonObject.put("token", token)
            jsonObject.put("uid", uid.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        AndroidNetworking.post(NODE_SERVER)
            .addJSONObjectBody(jsonObject) // posting json
            .setTag("test")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    Log.d("data-re",response.toString())
                }

                override fun onError(error: ANError) {
                    Log.d("data-error",error.toString())
                }
            })
    }
