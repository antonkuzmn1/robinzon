/**
 * Copyright 2024 Anton Kuzmin http://github.com/antonkuzmn1
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cloud.robinzon.backend.tools;

public interface ResponseTemplates {

    final StringBuilder sb = new StringBuilder();

    public String insert = "insert";
    public String update = "update";
    public String delete = "delete";

    default String setNull(String column) {
        return sb
                .append("\n")
                .append(column)
                .append(" cannot be null")
                .toString();
    }

    default String setChar(String column, int limit) {
        return sb
                .append("\n")
                .append(column)
                .append(" cannot contain more than ")
                .append(limit)
                .append(" characters")
                .toString();
    }

    default String setMore(String column, int limit) {
        return sb
                .append("\n")
                .append(column)
                .append(" cannot be more than ")
                .append(limit)
                .toString();
    }

    default String setLess(String column, int limit) {
        return sb
                .append("\n")
                .append(column)
                .append(" cannot be less than ")
                .append(limit)
                .toString();
    }

    default String inserted(String entity, String name) {
        return sb
                .append("Inserted new ")
                .append(entity)
                .append(": ")
                .append(name)
                .toString();
    }

    default String updated(String entity, String name) {
        return sb
                .append("Updated ")
                .append(entity)
                .append(": ")
                .append(name)
                .toString();
    }

    default String deleted(String entity, String name) {
        return sb
                .append("Deleted ")
                .append(entity)
                .append(": ")
                .append(name)
                .toString();
    }

}
