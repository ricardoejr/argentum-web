package br.com.caelum.caelum.argentum.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.ChartModel;

import bb.com.caelum.argentum.indicadores.IndicadorFactory;
import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.CandleFactory;
import br.com.caelum.argentum.Negociacao;
import br.com.caelum.argentum.SerieTemporal;
import br.com.caelum.argentum.ws.ClienteWebService;
import br.com.caelum.caelum.argentum.web.model.GeradorModeloGrafico;

@ManagedBean
@SessionScoped
public class ArgentumBean {

	private List<Negociacao> negociacoes;
	private ChartModel modeloGrafico;
	private String titulo;
	private String nomeIndicador;
	private String nomeMedia;

	public void preparaDados() {
		this.negociacoes = new ClienteWebService().getNegociacoes();
		List<Candle> candles = new CandleFactory()
				.constroiCandles(this.negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);
		GeradorModeloGrafico gerador = new GeradorModeloGrafico(serie, 2,
				serie.getTotal() - 1);

		IndicadorFactory indicadorFactory = new IndicadorFactory(getNomeIndicador(), getNomeMedia());
		
		gerador.plotaIndicador(indicadorFactory.getIndicador());

		this.modeloGrafico = gerador.getModeloGrafico();

	}

	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

	public ChartModel getModeloGrafico() {
		return modeloGrafico;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNomeIndicador() {
		return nomeIndicador;
	}

	public void setNomeIndicador(String nomeIndicador) {
		this.nomeIndicador = nomeIndicador;
	}

	public String getNomeMedia() {
		return nomeMedia;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

}
