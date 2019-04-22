package leyenda.model;
// Generated 22-abr-2019 18:21:49 by Hibernate Tools 5.1.10.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Princesadragon generated by hbm2java
 */
@Entity
@Table(name = "princesadragon", catalog = "llegenda")
public class Princesadragon implements java.io.Serializable {

	private String nombrePrincesa;
	private Rosaconjuros rosaconjuros;
	private String colorMagia;
	private Integer numeroConjuros;
	private String nombreDragon;
	private Integer ataqueDragon;
	private Integer vidaDragon;
	private Integer defensaDragon;
	private Set<Caballero> caballeros = new HashSet<Caballero>(0);

	public Princesadragon() {
	}

	public Princesadragon(String nombrePrincesa) {
		this.nombrePrincesa = nombrePrincesa;
	}

	public Princesadragon(String nombrePrincesa, Rosaconjuros rosaconjuros, String colorMagia, Integer numeroConjuros,
			String nombreDragon, Integer ataqueDragon, Integer vidaDragon, Integer defensaDragon,
			Set<Caballero> caballeros) {
		this.nombrePrincesa = nombrePrincesa;
		this.rosaconjuros = rosaconjuros;
		this.colorMagia = colorMagia;
		this.numeroConjuros = numeroConjuros;
		this.nombreDragon = nombreDragon;
		this.ataqueDragon = ataqueDragon;
		this.vidaDragon = vidaDragon;
		this.defensaDragon = defensaDragon;
		this.caballeros = caballeros;
	}

	@Id

	@Column(name = "nombrePrincesa", unique = true, nullable = false, length = 25)
	public String getNombrePrincesa() {
		return this.nombrePrincesa;
	}

	public void setNombrePrincesa(String nombrePrincesa) {
		this.nombrePrincesa = nombrePrincesa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nombreRosa")
	public Rosaconjuros getRosaconjuros() {
		return this.rosaconjuros;
	}

	public void setRosaconjuros(Rosaconjuros rosaconjuros) {
		this.rosaconjuros = rosaconjuros;
	}

	@Column(name = "colorMagia", length = 10)
	public String getColorMagia() {
		return this.colorMagia;
	}

	public void setColorMagia(String colorMagia) {
		this.colorMagia = colorMagia;
	}

	@Column(name = "numeroConjuros")
	public Integer getNumeroConjuros() {
		return this.numeroConjuros;
	}

	public void setNumeroConjuros(Integer numeroConjuros) {
		this.numeroConjuros = numeroConjuros;
	}

	@Column(name = "nombreDragon", length = 25)
	public String getNombreDragon() {
		return this.nombreDragon;
	}

	public void setNombreDragon(String nombreDragon) {
		this.nombreDragon = nombreDragon;
	}

	@Column(name = "ataqueDragon")
	public Integer getAtaqueDragon() {
		return this.ataqueDragon;
	}

	public void setAtaqueDragon(Integer ataqueDragon) {
		this.ataqueDragon = ataqueDragon;
	}

	@Column(name = "vidaDragon")
	public Integer getVidaDragon() {
		return this.vidaDragon;
	}

	public void setVidaDragon(Integer vidaDragon) {
		this.vidaDragon = vidaDragon;
	}

	@Column(name = "defensaDragon")
	public Integer getDefensaDragon() {
		return this.defensaDragon;
	}

	public void setDefensaDragon(Integer defensaDragon) {
		this.defensaDragon = defensaDragon;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "princesadragon")
	public Set<Caballero> getCaballeros() {
		return this.caballeros;
	}

	public void setCaballeros(Set<Caballero> caballeros) {
		this.caballeros = caballeros;
	}

}
