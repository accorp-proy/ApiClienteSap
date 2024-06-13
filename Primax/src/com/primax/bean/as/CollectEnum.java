package com.primax.bean.as;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.primax.ejb.lkp.BaseNaming;
import com.primax.jpa.enm.EstadoEnum;

@Named("collectorEM")
@ApplicationScoped
public class CollectEnum extends BaseNaming {

	public SelectItem[] getEstados() {
		List<EstadoEnum> estados = new ArrayList<>();
		for (EstadoEnum est : EstadoEnum.values()) {
			if (est.getGrupo() == 1)
				estados.add(est);
		}
		SelectItem[] items = new SelectItem[estados.size()];
		int i = 0;
		for (EstadoEnum g : estados) {
			items[i++] = new SelectItem(g, g.getDescripcion());
		}
		return items;
	}

	public SelectItem[] getEstadosUsuarios() {
		List<EstadoEnum> estados = new ArrayList<>();

		estados.add(EstadoEnum.ACT);
		estados.add(EstadoEnum.SUS);
		estados.add(EstadoEnum.INA);

		SelectItem[] items = new SelectItem[estados.size()];
		int i = 0;
		for (EstadoEnum g : estados) {
			items[i++] = new SelectItem(g, g.getDescripcion());
		}
		return items;
	}

	public SelectItem[] getEstadosCompleto() {
		SelectItem[] items = new SelectItem[EstadoEnum.values().length];
		int i = 0;
		for (EstadoEnum g : EstadoEnum.values()) {
			items[i++] = new SelectItem(g, g.getDescripcion());
		}
		return items;
	}

}
