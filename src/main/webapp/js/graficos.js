function tooltip(str, seriesIndex, pointIndex, plot) {
    return plot.series[seriesIndex]["label"] + ": " + str;
}

function configuracao_grafico() {
    this.cfg.seriesDefaults = {
        highlighter: {
            tooltipContentEditor: tooltip
        },
        rendererOptions: {
            highlightMouseDown: true
        }
    };
}