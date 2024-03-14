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

/**
 * <h3>String Templates for Response Form</h3>
 * <p>
 * This interface has string build templates for all occasions.
 * </p>
 * <p>
 * Contains the next methonds:
 * </p>
 * <p>
 * {@code setNull} - in obj cannot be null;
 * </p>
 * <p>
 * {@code setChar} - if obj contains more than X characters;
 * </p>
 * <p>
 * {@code setMore} - if value cannot be more than X;
 * </p>
 * <p>
 * {@code setLess} - if value cannot be less than X;
 * </p>
 * <p>
 * {@code inserted} - if new entity has been succefully inserted;
 * </p>
 * <p>
 * {@code setMore} - if entity has been succefully updated;
 * </p>
 * <p>
 * {@code setMore} - if entity has been succefully deleted;
 * </p>
 * <p>
 * Read more in the attached documents for each method.
 * </p>
 *
 * @since 2024.03.13
 * @author Anton Kuzmin
 */

public interface ResponseStringTemplates {

    /**
     * String builder is used in each of the functions of this class,
     * so it is based at the root.
     */
    final StringBuilder sb = new StringBuilder();

    /**
     * <h3>insert</h3>
     * <p>
     * Prepared variable for auto-completion when specifying a function name.
     * </p>
     */
    public final String insert = "insert";

    /**
     * <h3>update</h3>
     * <p>
     * Prepared variable for auto-completion when specifying a function name.
     * </p>
     */
    public final String update = "update";

    /**
     * <h3>delete</h3>
     * <p>
     * Prepared variable for auto-completion when specifying a function name.
     * </p>
     */
    public final String delete = "delete";

    /**
     * <h3>Object cannot be null</h3>
     * <p>
     * Use this pattern if the object must not be null
     * </p>
     *
     * @param column - enter column name;
     * @return A ready template string.
     *
     * @since 2024.03.13
     * @author Anton Kuzmin
     */
    default String setNull(String column) {
        return sb
                .append("\n")
                .append(column)
                .append(" cannot be null")
                .toString();
    }

    /**
     * <h3>String cannot contain more than X characters</h3>
     * <p>
     * Use this pattern if the string must cannot contain more than X characters
     * </p>
     *
     * @param column - the column name;
     * @param limit  - the line length limit;
     * @return A ready template string.
     *
     * @since 2024.03.13
     * @author Anton Kuzmin
     */
    default String setChar(String column, int limit) {
        return sb
                .append("\n")
                .append(column)
                .append(" cannot contain more than ")
                .append(limit)
                .append(" characters")
                .toString();
    }

    /**
     * <h3>Value cannot be more than X</h3>
     * <p>
     * Use this pattern if the value must cannot be more than X
     * </p>
     *
     * @param column - the column name;
     * @param limit  - maximum value;
     * @return A ready template string.
     *
     * @since 2024.03.13
     * @author Anton Kuzmin
     */
    default String setMore(String column, int limit) {
        return sb
                .append("\n")
                .append(column)
                .append(" cannot be more than ")
                .append(limit)
                .toString();
    }

    /**
     * <h3>Value cannot be less than X</h3>
     * <p>
     * Use this pattern if the value must cannot be less than X
     * </p>
     *
     * @param column - the column name;
     * @param limit  - minimum value;
     * @return A ready template string.
     *
     * @since 2024.03.13
     * @author Anton Kuzmin
     */
    default String setLess(String column, int limit) {
        return sb
                .append("\n")
                .append(column)
                .append(" cannot be less than ")
                .append(limit)
                .toString();
    }

    /**
     * <h3>The new entity was successfully inserted into the database</h3>
     * <p>
     * Use this method along with {@code super.success(here)};
     * </p>
     *
     * @param entity - inserted entity;
     * @param name   - information about the entity that will be displayed in the
     *               logs (name for example);
     * @return A ready template string.
     *
     * @since 2024.03.13
     * @author Anton Kuzmin
     */
    default String inserted(String name) {
        return sb
                .append("Inserted: ")
                .append(name)
                .toString();
    }

    /**
     * <h3>The entity was successfully updated</h3>
     * <p>
     * Use this method along with {@code super.success(here)};
     * </p>
     *
     * @param entity - updated entity;
     * @param name   - information about the entity that will be displayed in the
     *               logs (name for example);
     * @return A ready template string.
     *
     * @since 2024.03.13
     * @author Anton Kuzmin
     */
    default String updated(String name) {
        return sb
                .append("Updated: ")
                .append(name)
                .toString();
    }

    /**
     * <h3>The entity was successfully deleted</h3>
     * <p>
     * Use this method along with {@code super.success(here)};
     * </p>
     *
     * @param entity - deleted entity;
     * @param name   - information about the entity that will be displayed in the
     *               logs (name for example);
     * @return A ready template string.
     *
     * @since 2024.03.13
     * @author Anton Kuzmin
     */
    default String deleted(String name) {
        return sb
                .append("Deleted: ")
                .append(name)
                .toString();
    }

}
