package leyenda.model;
// Generated 22-abr-2019 18:21:49 by Hibernate Tools 5.1.10.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tiene generated by hbm2java
 */
@Entity
@Table(name = "tiene", catalog = "llegenda")
public class Tiene implements java.io.Serializable {

	private TieneId id;
	private Caballero caballero;
	private Espada espada;
	private Integer porcentageAtaque;

	public Tiene() {
	}

	public Tiene(TieneId id, Caballero caballero, Espada espada) {
		this.id = id;
		this.caballero = caballero;
		this.espada = espada;
	}

	public Tiene(TieneId id, Caballero caballero, Espada espada, Integer porcentageAtaque) {
		this.id = id;
		this.caballero = caballero;
		this.espada = espada;
		this.porcentageAtaque = porcentageAtaque;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "nombreCaballero", column = @Column(name = "nombreCaballero", nullable = false, length = 25)),
			@AttributeOverride(name = "nombreEspada", column = @Column(name = "nombreEspada", nullable = false, length = 25)) })
	public TieneId getId() {
		return this.id;
	}

	public void setId(TieneId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nombreCaballero", nullable = false, insertable = false, updatable = false)
	public Caballero getCaballero() {
		return this.caballero;
	}

	public void setCaballero(Caballero caballero) {
		this.caballero = caballero;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nombreEspada", nullable = false, insertable = false, updatable = false)
	public Espada getEspada() {
		return this.espada;
	}

	public void setEspada(Espada espada) {
		this.espada = espada;
	}

	@Column(name = "porcentageAtaque")
	public Integer getPorcentageAtaque() {
		return this.porcentageAtaque;
	}

	public void setPorcentageAtaque(Integer porcentageAtaque) {
		this.porcentageAtaque = porcentageAtaque;
	}

}