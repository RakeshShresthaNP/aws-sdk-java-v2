package software.amazon.awssdk.services.query.waiters;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import software.amazon.awssdk.annotations.Generated;
import software.amazon.awssdk.annotations.SdkInternalApi;
import software.amazon.awssdk.annotations.ThreadSafe;
import software.amazon.awssdk.awscore.AwsRequestOverrideConfiguration;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ApiName;
import software.amazon.awssdk.core.internal.waiters.WaiterAttribute;
import software.amazon.awssdk.core.waiters.Waiter;
import software.amazon.awssdk.core.waiters.WaiterAcceptor;
import software.amazon.awssdk.core.waiters.WaiterOverrideConfiguration;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.core.waiters.WaiterState;
import software.amazon.awssdk.retries.api.BackoffStrategy;
import software.amazon.awssdk.services.query.QueryClient;
import software.amazon.awssdk.services.query.jmespath.internal.JmesPathRuntime;
import software.amazon.awssdk.services.query.model.APostOperationRequest;
import software.amazon.awssdk.services.query.model.APostOperationResponse;
import software.amazon.awssdk.services.query.model.QueryRequest;
import software.amazon.awssdk.services.query.waiters.internal.WaitersRuntime;
import software.amazon.awssdk.utils.AttributeMap;
import software.amazon.awssdk.utils.SdkAutoCloseable;

@Generated("software.amazon.awssdk:codegen")
@SdkInternalApi
@ThreadSafe
final class DefaultQueryWaiter implements QueryWaiter {
    private static final WaiterAttribute<SdkAutoCloseable> CLIENT_ATTRIBUTE = new WaiterAttribute<>(SdkAutoCloseable.class);

    private final QueryClient client;

    private final AttributeMap managedResources;

    private final Waiter<APostOperationResponse> postOperationSuccessWaiter;

    private final Waiter<APostOperationResponse> floatValueTestWaiter;

    private final Waiter<APostOperationResponse> bigDecimalValueTestWaiter;

    private final Waiter<APostOperationResponse> longValueTestWaiter;

    private final Waiter<APostOperationResponse> doubleValueTestWaiter;

    private DefaultQueryWaiter(DefaultBuilder builder) {
        AttributeMap.Builder attributeMapBuilder = AttributeMap.builder();
        if (builder.client == null) {
            this.client = QueryClient.builder().build();
            attributeMapBuilder.put(CLIENT_ATTRIBUTE, this.client);
        } else {
            this.client = builder.client;
        }
        managedResources = attributeMapBuilder.build();
        this.postOperationSuccessWaiter = Waiter.builder(APostOperationResponse.class)
                                                .acceptors(postOperationSuccessWaiterAcceptors())
                                                .overrideConfiguration(postOperationSuccessWaiterConfig(builder.overrideConfiguration)).build();
        this.floatValueTestWaiter = Waiter.builder(APostOperationResponse.class).acceptors(floatValueTestWaiterAcceptors())
                                          .overrideConfiguration(floatValueTestWaiterConfig(builder.overrideConfiguration)).build();
        this.bigDecimalValueTestWaiter = Waiter.builder(APostOperationResponse.class)
                                               .acceptors(bigDecimalValueTestWaiterAcceptors())
                                               .overrideConfiguration(bigDecimalValueTestWaiterConfig(builder.overrideConfiguration)).build();
        this.longValueTestWaiter = Waiter.builder(APostOperationResponse.class).acceptors(longValueTestWaiterAcceptors())
                                         .overrideConfiguration(longValueTestWaiterConfig(builder.overrideConfiguration)).build();
        this.doubleValueTestWaiter = Waiter.builder(APostOperationResponse.class).acceptors(doubleValueTestWaiterAcceptors())
                                           .overrideConfiguration(doubleValueTestWaiterConfig(builder.overrideConfiguration)).build();
    }

    private static String errorCode(Throwable error) {
        if (error instanceof AwsServiceException) {
            return ((AwsServiceException) error).awsErrorDetails().errorCode();
        }
        return null;
    }

    @Override
    public WaiterResponse<APostOperationResponse> waitUntilBigDecimalValueTest(APostOperationRequest aPostOperationRequest) {
        return bigDecimalValueTestWaiter.run(() -> client.aPostOperation(applyWaitersUserAgent(aPostOperationRequest)));
    }

    @Override
    public WaiterResponse<APostOperationResponse> waitUntilBigDecimalValueTest(APostOperationRequest aPostOperationRequest,
                                                                               WaiterOverrideConfiguration overrideConfig) {
        return bigDecimalValueTestWaiter.run(() -> client.aPostOperation(applyWaitersUserAgent(aPostOperationRequest)),
                                             bigDecimalValueTestWaiterConfig(overrideConfig));
    }

    @Override
    public WaiterResponse<APostOperationResponse> waitUntilDoubleValueTest(APostOperationRequest aPostOperationRequest) {
        return doubleValueTestWaiter.run(() -> client.aPostOperation(applyWaitersUserAgent(aPostOperationRequest)));
    }

    @Override
    public WaiterResponse<APostOperationResponse> waitUntilDoubleValueTest(APostOperationRequest aPostOperationRequest,
                                                                           WaiterOverrideConfiguration overrideConfig) {
        return doubleValueTestWaiter.run(() -> client.aPostOperation(applyWaitersUserAgent(aPostOperationRequest)),
                                         doubleValueTestWaiterConfig(overrideConfig));
    }

    @Override
    public WaiterResponse<APostOperationResponse> waitUntilFloatValueTest(APostOperationRequest aPostOperationRequest) {
        return floatValueTestWaiter.run(() -> client.aPostOperation(applyWaitersUserAgent(aPostOperationRequest)));
    }

    @Override
    public WaiterResponse<APostOperationResponse> waitUntilFloatValueTest(APostOperationRequest aPostOperationRequest,
                                                                          WaiterOverrideConfiguration overrideConfig) {
        return floatValueTestWaiter.run(() -> client.aPostOperation(applyWaitersUserAgent(aPostOperationRequest)),
                                        floatValueTestWaiterConfig(overrideConfig));
    }

    @Override
    public WaiterResponse<APostOperationResponse> waitUntilLongValueTest(APostOperationRequest aPostOperationRequest) {
        return longValueTestWaiter.run(() -> client.aPostOperation(applyWaitersUserAgent(aPostOperationRequest)));
    }

    @Override
    public WaiterResponse<APostOperationResponse> waitUntilLongValueTest(APostOperationRequest aPostOperationRequest,
                                                                         WaiterOverrideConfiguration overrideConfig) {
        return longValueTestWaiter.run(() -> client.aPostOperation(applyWaitersUserAgent(aPostOperationRequest)),
                                       longValueTestWaiterConfig(overrideConfig));
    }

    @Override
    public WaiterResponse<APostOperationResponse> waitUntilPostOperationSuccess(APostOperationRequest aPostOperationRequest) {
        return postOperationSuccessWaiter.run(() -> client.aPostOperation(applyWaitersUserAgent(aPostOperationRequest)));
    }

    @Override
    public WaiterResponse<APostOperationResponse> waitUntilPostOperationSuccess(APostOperationRequest aPostOperationRequest,
                                                                                WaiterOverrideConfiguration overrideConfig) {
        return postOperationSuccessWaiter.run(() -> client.aPostOperation(applyWaitersUserAgent(aPostOperationRequest)),
                                              postOperationSuccessWaiterConfig(overrideConfig));
    }

    private static List<WaiterAcceptor<? super APostOperationResponse>> postOperationSuccessWaiterAcceptors() {
        List<WaiterAcceptor<? super APostOperationResponse>> result = new ArrayList<>();
        result.add(new WaitersRuntime.ResponseStatusAcceptor(200, WaiterState.SUCCESS));
        result.add(new WaitersRuntime.ResponseStatusAcceptor(404, WaiterState.RETRY));
        result.add(WaiterAcceptor.successOnResponseAcceptor(response -> {
            JmesPathRuntime.Value input = new JmesPathRuntime.Value(response);
            List<Object> resultValues = input.field("foo").field("bar").values();
            return !resultValues.isEmpty() && resultValues.stream().anyMatch(v -> Objects.equals(v, "baz"));
        }));
        result.addAll(WaitersRuntime.DEFAULT_ACCEPTORS);
        return result;
    }

    private static List<WaiterAcceptor<? super APostOperationResponse>> floatValueTestWaiterAcceptors() {
        List<WaiterAcceptor<? super APostOperationResponse>> result = new ArrayList<>();
        result.add(WaiterAcceptor.successOnResponseAcceptor(response -> {
            JmesPathRuntime.Value input = new JmesPathRuntime.Value(response);
            return Objects.equals(input.field("FloatValue").value(), new BigDecimal("42.5"));
        }));
        result.addAll(WaitersRuntime.DEFAULT_ACCEPTORS);
        return result;
    }

    private static List<WaiterAcceptor<? super APostOperationResponse>> bigDecimalValueTestWaiterAcceptors() {
        List<WaiterAcceptor<? super APostOperationResponse>> result = new ArrayList<>();
        result.add(WaiterAcceptor.successOnResponseAcceptor(response -> {
            JmesPathRuntime.Value input = new JmesPathRuntime.Value(response);
            return Objects.equals(input.field("BigDecimalValue").value(), new BigDecimal(
                "123132.81289319837183771465876127837183719837123"));
        }));
        result.addAll(WaitersRuntime.DEFAULT_ACCEPTORS);
        return result;
    }

    private static List<WaiterAcceptor<? super APostOperationResponse>> longValueTestWaiterAcceptors() {
        List<WaiterAcceptor<? super APostOperationResponse>> result = new ArrayList<>();
        result.add(WaiterAcceptor.successOnResponseAcceptor(response -> {
            JmesPathRuntime.Value input = new JmesPathRuntime.Value(response);
            return Objects.equals(input.field("LongValue").value(), new BigDecimal("9223372036854775807"));
        }));
        result.addAll(WaitersRuntime.DEFAULT_ACCEPTORS);
        return result;
    }

    private static List<WaiterAcceptor<? super APostOperationResponse>> doubleValueTestWaiterAcceptors() {
        List<WaiterAcceptor<? super APostOperationResponse>> result = new ArrayList<>();
        result.add(WaiterAcceptor.successOnResponseAcceptor(response -> {
            JmesPathRuntime.Value input = new JmesPathRuntime.Value(response);
            return Objects.equals(input.field("DoubleValue").value(), new BigDecimal("1.7976931348623157E+308"));
        }));
        result.addAll(WaitersRuntime.DEFAULT_ACCEPTORS);
        return result;
    }

    private static WaiterOverrideConfiguration postOperationSuccessWaiterConfig(WaiterOverrideConfiguration overrideConfig) {
        Optional<WaiterOverrideConfiguration> optionalOverrideConfig = Optional.ofNullable(overrideConfig);
        int maxAttempts = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::maxAttempts).orElse(40);
        BackoffStrategy backoffStrategy = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::backoffStrategyV2).orElse(
            BackoffStrategy.fixedDelayWithoutJitter(Duration.ofSeconds(1)));
        Duration waitTimeout = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::waitTimeout).orElse(null);
        return WaiterOverrideConfiguration.builder().maxAttempts(maxAttempts).backoffStrategyV2(backoffStrategy)
                                          .waitTimeout(waitTimeout).build();
    }

    private static WaiterOverrideConfiguration floatValueTestWaiterConfig(WaiterOverrideConfiguration overrideConfig) {
        Optional<WaiterOverrideConfiguration> optionalOverrideConfig = Optional.ofNullable(overrideConfig);
        int maxAttempts = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::maxAttempts).orElse(40);
        BackoffStrategy backoffStrategy = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::backoffStrategyV2).orElse(
            BackoffStrategy.fixedDelayWithoutJitter(Duration.ofSeconds(15)));
        Duration waitTimeout = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::waitTimeout).orElse(null);
        return WaiterOverrideConfiguration.builder().maxAttempts(maxAttempts).backoffStrategyV2(backoffStrategy)
                                          .waitTimeout(waitTimeout).build();
    }

    private static WaiterOverrideConfiguration bigDecimalValueTestWaiterConfig(WaiterOverrideConfiguration overrideConfig) {
        Optional<WaiterOverrideConfiguration> optionalOverrideConfig = Optional.ofNullable(overrideConfig);
        int maxAttempts = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::maxAttempts).orElse(40);
        BackoffStrategy backoffStrategy = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::backoffStrategyV2).orElse(
            BackoffStrategy.fixedDelayWithoutJitter(Duration.ofSeconds(15)));
        Duration waitTimeout = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::waitTimeout).orElse(null);
        return WaiterOverrideConfiguration.builder().maxAttempts(maxAttempts).backoffStrategyV2(backoffStrategy)
                                          .waitTimeout(waitTimeout).build();
    }

    private static WaiterOverrideConfiguration longValueTestWaiterConfig(WaiterOverrideConfiguration overrideConfig) {
        Optional<WaiterOverrideConfiguration> optionalOverrideConfig = Optional.ofNullable(overrideConfig);
        int maxAttempts = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::maxAttempts).orElse(40);
        BackoffStrategy backoffStrategy = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::backoffStrategyV2).orElse(
            BackoffStrategy.fixedDelayWithoutJitter(Duration.ofSeconds(15)));
        Duration waitTimeout = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::waitTimeout).orElse(null);
        return WaiterOverrideConfiguration.builder().maxAttempts(maxAttempts).backoffStrategyV2(backoffStrategy)
                                          .waitTimeout(waitTimeout).build();
    }

    private static WaiterOverrideConfiguration doubleValueTestWaiterConfig(WaiterOverrideConfiguration overrideConfig) {
        Optional<WaiterOverrideConfiguration> optionalOverrideConfig = Optional.ofNullable(overrideConfig);
        int maxAttempts = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::maxAttempts).orElse(40);
        BackoffStrategy backoffStrategy = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::backoffStrategyV2).orElse(
            BackoffStrategy.fixedDelayWithoutJitter(Duration.ofSeconds(15)));
        Duration waitTimeout = optionalOverrideConfig.flatMap(WaiterOverrideConfiguration::waitTimeout).orElse(null);
        return WaiterOverrideConfiguration.builder().maxAttempts(maxAttempts).backoffStrategyV2(backoffStrategy)
                                          .waitTimeout(waitTimeout).build();
    }

    @Override
    public void close() {
        managedResources.close();
    }

    public static QueryWaiter.Builder builder() {
        return new DefaultBuilder();
    }

    private <T extends QueryRequest> T applyWaitersUserAgent(T request) {
        Consumer<AwsRequestOverrideConfiguration.Builder> userAgentApplier = b -> b.addApiName(ApiName.builder()
                                                                                                      .name("sdk-metrics").version("B").build());
        AwsRequestOverrideConfiguration overrideConfiguration = request.overrideConfiguration()
                                                                       .map(c -> c.toBuilder().applyMutation(userAgentApplier).build())
                                                                       .orElse((AwsRequestOverrideConfiguration.builder().applyMutation(userAgentApplier).build()));
        return (T) request.toBuilder().overrideConfiguration(overrideConfiguration).build();
    }

    public static final class DefaultBuilder implements QueryWaiter.Builder {
        private QueryClient client;

        private WaiterOverrideConfiguration overrideConfiguration;

        private DefaultBuilder() {
        }

        @Override
        public QueryWaiter.Builder overrideConfiguration(WaiterOverrideConfiguration overrideConfiguration) {
            this.overrideConfiguration = overrideConfiguration;
            return this;
        }

        @Override
        public QueryWaiter.Builder client(QueryClient client) {
            this.client = client;
            return this;
        }

        public QueryWaiter build() {
            return new DefaultQueryWaiter(this);
        }
    }
}
