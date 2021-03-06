package com.flair.bi.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flair.bi.domain.Feature;
import com.flair.bi.domain.View;
import com.flair.bi.domain.ViewFeatureCriteria;
import com.flair.bi.repository.FeatureRepository;
import com.flair.bi.repository.ViewRepository;
import com.flair.bi.web.rest.dto.CreateViewFeatureCriteriaRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ViewFeatureCriteriaService {

	private final FeatureRepository featureRepository;

	private final ViewRepository viewRepository;

	public Set<ViewFeatureCriteria> create(CreateViewFeatureCriteriaRequest request) {
		View view = viewRepository.getOne(request.getViewId());

    final Set<ViewFeatureCriteria> viewFeatureCriterias = request.getFeatures().stream()
				.map(f -> composeViewFeatureCriteria(view, f)).collect(Collectors.toSet());
    // remove existing one
    view.getViewFeatureCriterias().forEach(view::remove);

    //add new one
    viewFeatureCriterias.forEach(view::add);

		viewRepository.save(view);

		return view.getViewFeatureCriterias();
	}

	private ViewFeatureCriteria composeViewFeatureCriteria(View view,
			CreateViewFeatureCriteriaRequest.ViewFeatureData f) {
		Feature feature = featureRepository.getOne(f.getFeatureId());
		ViewFeatureCriteria viewFeatureCriteria = new ViewFeatureCriteria();
		viewFeatureCriteria.setFeature(feature);
		viewFeatureCriteria.setView(view);
		viewFeatureCriteria.setValue(f.getValue());
		viewFeatureCriteria.setMetadata(f.getMetadata());
		return viewFeatureCriteria;
	}
}
