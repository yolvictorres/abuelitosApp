/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2017-02-15 17:18:02 UTC)
 * on 2017-06-01 at 16:16:34 UTC 
 * Modify at your own risk.
 */

package co.edu.konradlorenz.a506132023.abuelitosservices.backend.foroApi.model;

/**
 * Model definition for Foro.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the foroApi. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Foro extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String comentario;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String usuario;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getComentario() {
    return comentario;
  }

  /**
   * @param comentario comentario or {@code null} for none
   */
  public Foro setComentario(java.lang.String comentario) {
    this.comentario = comentario;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public Foro setId(java.lang.String id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getUsuario() {
    return usuario;
  }

  /**
   * @param usuario usuario or {@code null} for none
   */
  public Foro setUsuario(java.lang.String usuario) {
    this.usuario = usuario;
    return this;
  }

  @Override
  public Foro set(String fieldName, Object value) {
    return (Foro) super.set(fieldName, value);
  }

  @Override
  public Foro clone() {
    return (Foro) super.clone();
  }

}
