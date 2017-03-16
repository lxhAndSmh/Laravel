/*
 * Copyright 2016 Freelander
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liu.laravel.bean.topic;

import java.util.List;

/**
* 项目名称：TopicList
* 类描述：
* 创建人：liuxuhui
* 创建时间：2017/3/16 16:41
* 修改人：liuxuhui
* 修改时间：2017/3/16 16:41
* 修改备注：
* @version
*/
public class TopicList {

    private List<Topic> data;

    public List<Topic> getData() {
        return data;
    }

    public void setData(List<Topic> data) {
        this.data = data;
    }

}
